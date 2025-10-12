# POS Portrait Layout Design

## Layout Structure

The POS screen has been redesigned for portrait mode with a clean 50/50 split:

### Upper Half (50% height)
**Categories + Products Selection**

```
┌─────────────────────────────────────────────────┐
│ ┌─────────────┐ ┌─────────────────────────────┐ │
│ │ Categories  │ │        Products Area        │ │
│ │    Card     │ │                             │ │
│ │             │ │ ┌─────────────────────────┐ │ │
│ │ ┌─────────┐ │ │ │    Search Bar           │ │ │
│ │ │   All   │ │ │ └─────────────────────────┘ │ │
│ │ ├─────────┤ │ │ ┌─────────────────────────┐ │ │
│ │ │Category1│ │ │ │                         │ │ │
│ │ ├─────────┤ │ │ │    Products Grid        │ │ │
│ │ │Category2│ │ │ │   (ProductsGridFragment)│ │ │
│ │ └─────────┘ │ │ │                         │ │ │
│ └─────────────┘ │ └─────────────────────────┘ │ │
└─────────────────────────────────────────────────┘
```

### Lower Half (50% height) 
**Ticket Info + Cart + Actions**

```
┌─────────────────────────────────────────────────┐
│ ┌─────────────────────────────────────────────┐ │
│ │    Ticket #001              3 items         │ │
│ └─────────────────────────────────────────────┘ │
│ ┌─────────────────────┐ ┌─────────────────────┐ │
│ │                     │ │    Totals Card      │ │
│ │                     │ │ Subtotal: RM 15.00  │ │
│ │    Cart Items       │ │ Tax:      RM  2.25  │ │
│ │    List             │ │ ─────────────────   │ │
│ │                     │ │ Total:    RM 17.25  │ │
│ │                     │ └─────────────────────┘ │
│ │                     │ ┌─────────────────────┐ │
│ │                     │ │       PAY           │ │
│ │                     │ ├─────────────────────┤ │
│ │                     │ │     Discount        │ │
│ │                     │ ├─────────────────────┤ │
│ │                     │ │       Hold          │ │
│ └─────────────────────┘ └─────────────────────┘ │
└─────────────────────────────────────────────────┘
```

## Key Features

### Categories Section
- **Compact card design** with header "Categories"
- **Vertical scrollable** button list
- **120dp width** for portrait optimization
- **12sp text size** for compact buttons
- **Visual selection** with primary color highlight

### Products Section
- **Search bar** at the top for quick product lookup  
- **Products grid** using existing ProductsGridFragment
- **Card container** with consistent elevation and rounded corners

### Cart & Actions Section
- **Ticket header** showing ticket number and item count
- **Side-by-side layout** with cart list and action panel
- **Compact totals** display in dedicated card
- **Prominent PAY button** (2x height) in primary color
- **Secondary actions** (Discount, Hold) as outlined buttons

## Responsive Design
- **Fixed 50/50 split** ensures consistent layout
- **Card-based design** provides visual separation
- **Proper margins** and padding for touch-friendly interface
- **Consistent color scheme** using material design principles

## Technical Implementation
- **LinearLayout** with vertical orientation and equal weights
- **MaterialCardView** for consistent elevation and corners
- **Proper constraint** relationships for responsive behavior
- **Dynamic item count** updates based on cart contents
- **Category buttons** generated from database with real-time updates
- **Single search bar** - ProductsGridFragment internal search is properly hidden
- **Search integration** - POS search bar wired to ProductsGridFragment.setSearchQuery()