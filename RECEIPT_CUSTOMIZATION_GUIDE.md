# Receipt Customization System - AndroPOS

## Overview
The Receipt Customization system in AndroPOS allows comprehensive customization of receipt printing including paper size selection, store information, and format options. This system integrates with the GlobalPrinterService to provide proper ESC/POS formatting for thermal printers.

## Features Implemented

### 1. Paper Size Selection (58mm vs 80mm)
- **58mm (2.3 inch)**: Standard thermal receipt paper
  - Characters per line: 32
  - ESC/POS width command: `\u001D\u0057\u0020`
  - Ideal for compact receipts and small printers
- **80mm (3.1 inch)**: Wide thermal receipt paper
  - Characters per line: 48
  - ESC/POS width command: `\u001D\u0057\u0030`
  - Better for detailed receipts with more information

### 2. Store Information Customization
- **Store Name**: Customizable business name displayed at receipt header
- **Store Address**: Multi-line address with automatic text wrapping
- **Phone Number**: Contact information for customer inquiries
- **Footer Message**: Customizable thank you message and branding

### 3. Format Options
- **Store Logo**: Toggle to show/hide store logo placeholder
- **QR Code**: Include QR code for customer feedback
- **Tax Breakdown**: Show detailed SST (6%) breakdown
- **Auto-cut**: Automatic paper cutting after printing
- **Duplicate Receipt**: Print two copies automatically

### 4. Real-time Preview and Testing
- **Preview Receipt**: View formatted receipt text in dialog
- **Print Sample**: Send test receipt to connected printer
- **Live Settings**: Changes reflect immediately in preview

## Technical Architecture

### Core Classes

#### ReceiptSettings Data Class
```kotlin
data class ReceiptSettings(
    val paperSize: PaperSize = PaperSize.MM_58,
    val charactersPerLine: Int = 32,
    val storeName: String = "AndroPOS Store",
    // ... other properties
)
```

**Key Features:**
- Enum-based paper size with automatic character calculation
- ESC/POS command generation methods
- Text formatting and centering utilities
- Automatic line wrapping for different paper widths

#### ReceiptSettingsRepository
```kotlin
@Singleton
class ReceiptSettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
)
```

**Functionality:**
- SharedPreferences-based persistence
- JSON serialization with Gson
- Individual field access for performance
- Graceful fallback to defaults

#### HardwareSettingsViewModel
```kotlin
@HiltViewModel
class HardwareSettingsViewModel @Inject constructor(
    private val receiptSettingsRepository: ReceiptSettingsRepository
)
```

**Features:**
- StateFlow-based reactive UI updates
- Async save/load operations
- Real-time settings validation
- Status feedback for save operations

### GlobalPrinterService Integration

#### Enhanced Printing Methods
```kotlin
suspend fun printCustomizedReceipt(
    receiptSettings: ReceiptSettings, 
    receiptData: Map<String, Any>
): Boolean
```

**ESC/POS Commands Used:**
- `\u001B@`: Initialize printer
- `\u001B\u0061\u0001`: Center alignment
- `\u001B\u0061\u0000`: Left alignment
- `\u001D\u0056\u0042\u0000`: Full cut command
- Paper width commands based on size selection

#### Receipt Generation Features
- Proper alignment for different paper sizes
- Currency formatting (Malaysian Ringgit - RM)
- Date/time formatting with locale support
- Item listing with quantity and pricing
- Tax calculation and breakdown display
- Professional receipt layout with separators

## User Interface

### Navigation Path
```
Main Menu → Settings → Hardware & Device Settings → Receipt Customization
```

### UI Components

#### Paper Size Selection
- Radio group with 58mm and 80mm options
- Real-time character count updates
- Visual indicators for paper size differences

#### Store Information Forms
- Material Design text input layouts
- Multi-line support for addresses
- Phone number input validation
- Footer message customization

#### Format Options
- Material Design checkboxes
- Toggle switches for boolean options
- Immediate UI feedback

#### Action Buttons
- **Preview Receipt**: Shows formatted text preview
- **Print Sample**: Sends test receipt to printer
- **Save Settings**: Persists all changes

### Card-based Layout
Each section is organized in Material Design cards:
1. **Paper Size Card**: Radio group selection
2. **Receipt Content Card**: Store information forms
3. **Format Options Card**: Boolean toggle options
4. **Actions Card**: Preview and print buttons

## Usage Instructions

### Basic Setup
1. Navigate to Settings → Hardware & Device Settings
2. Scroll to "Receipt Customization" section
3. Select paper size (58mm or 80mm)
4. Fill in store information
5. Configure format options
6. Save settings

### Testing Receipt Format
1. Connect to printer via Printer Setup
2. Configure receipt settings
3. Click "Preview Receipt" to see formatted text
4. Click "Print Sample" to test actual printing
5. Verify receipt appearance and adjust settings

### Integration with Sales
The receipt settings automatically apply to all receipts printed through:
- Sales transactions
- Order confirmations
- Test prints
- Sample receipts

## Sample Receipt Output

### 58mm Paper Example
```
        ANDROPOS STORE
    123 Main Street
   Kuala Lumpur, Malaysia
     Tel: +60 3-1234 5678

================================
Receipt #: SAMPLE001
Date: 29/09/2024 16:45:32
Cashier: Demo User
================================

ITEMS:
Nasi Lemak x2        RM12.00
Teh Tarik x1         RM3.50
Roti Canai x3        RM4.50
--------------------------------
Subtotal:            RM20.00
SST (6%):            RM1.20
TOTAL:               RM21.20
Cash:                RM25.00
Change:              RM3.80
================================

        [QR CODE]
     Scan for feedback

    Thank you for business!
     Powered by AndroPOS
```

### 80mm Paper Example
```
                ANDROPOS STORE
          123 Main Street
         Kuala Lumpur, Malaysia
           Tel: +60 3-1234 5678

================================================
Receipt #: SAMPLE001
Date: 29/09/2024 16:45:32
Cashier: Demo User
================================================

ITEMS:
Nasi Lemak x2                        RM12.00
Teh Tarik x1                         RM3.50
Roti Canai x3                        RM4.50
------------------------------------------------
Subtotal:                            RM20.00
SST (6%):                            RM1.20
TOTAL:                               RM21.20
Cash:                                RM25.00
Change:                              RM3.80
================================================

              [QR CODE]
           Scan for feedback

      Thank you for your business!
           Powered by AndroPOS
```

## Configuration Options

### SharedPreferences Keys
- `receipt_settings_json`: Complete settings object
- `paper_size`: Quick access to paper size
- `store_name`: Store name only
- Individual fields for all settings

### Default Values
- Paper Size: 58mm (32 characters per line)
- Store Name: "AndroPOS Store"
- Currency: "RM" (Malaysian Ringgit)
- Auto-cut: Enabled
- Tax breakdown: Enabled
- Logo display: Enabled

## Error Handling

### Connection Issues
- Graceful degradation when printer unavailable
- Clear error messages for connection failures
- Retry mechanisms for failed prints

### Settings Validation
- Required field validation
- Format validation for phone numbers
- Character limit warnings for small paper sizes

### Persistence Failures
- JSON parsing error recovery
- Individual field fallback loading
- Reset to defaults option

## Performance Considerations

### Efficient Storage
- JSON serialization for complex objects
- Individual field caching for quick access
- Minimal SharedPreferences operations

### Print Performance
- Pre-generated ESC/POS commands
- Optimized string building
- Async print operations with progress feedback

### Memory Management
- StateFlow for reactive updates
- Proper lifecycle-aware operations
- Cleanup of printer connections

## Testing and Validation

### Unit Tests Coverage
- ReceiptSettings data class methods
- Text formatting and alignment functions
- ESC/POS command generation
- Repository save/load operations

### Integration Testing
- End-to-end receipt printing flow
- Settings persistence across app restarts
- Multiple paper size compatibility
- Bluetooth printer communication

### Manual Testing Checklist
- [ ] Paper size changes reflect in preview
- [ ] Store information updates correctly
- [ ] Format options toggle properly
- [ ] Sample printing works with connected printer
- [ ] Settings persist after app restart
- [ ] Different paper sizes format correctly
- [ ] Malaysian currency displays properly

## Future Enhancements

### Planned Features
- Custom logo image upload
- Font size selection (small/normal/large)
- Receipt template selection
- Multi-language support
- Custom field additions
- Receipt analytics integration

### Integration Points
- POS transaction system
- Inventory management receipt printing
- Customer management integration
- Analytics and reporting systems

## Troubleshooting

### Common Issues
1. **Settings not saving**: Check SharedPreferences permissions
2. **Preview not updating**: Verify UI binding and StateFlow observers
3. **Print formatting wrong**: Confirm paper size selection matches actual printer
4. **Printer not responding**: Verify GlobalPrinterService connection status

### Debug Information
- Enable logging for receipt generation
- Test with different paper sizes
- Verify ESC/POS command sequences
- Check character count calculations

This comprehensive receipt customization system provides professional-grade receipt printing capabilities with full user control over format, content, and appearance while maintaining compatibility with thermal printer standards.